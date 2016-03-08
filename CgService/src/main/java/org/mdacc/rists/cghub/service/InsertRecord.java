package org.mdacc.rists.cghub.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.mdacc.rists.cghub.dao.CghubDao;
import org.mdacc.rists.cghub.model.SeqTb;
import org.mdacc.rists.cghub.model.GroupTb;
import org.mdacc.rists.cghub.model.PairTb;


/**
 * InserDatabase.java
 * Purpose: Insert cghub meta data into  Mysql database.
 * @author Oscar Jiao
 */


public class InsertRecord {
	
	static int recordCount = 0;
	
    public static void main(String[] args) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CghubDB");
    	EntityManager manager = factory.createEntityManager();
		CghubDao dao = new CghubDao(manager);

        try
        {
        	// -S for single file loading
    		if (args[0].equalsIgnoreCase("-S")) {
    			
    			if (args.length == 2) {
    				readSingles(args[1], dao);
    			}
    			else {
    				System.out.print("Usage: InsertRecord -S [path to dirs/files].");
    				System.exit(1);
    			}
    		}
    		else if (args[0].equalsIgnoreCase("-P")) { // pair record mode
    			if (args.length == 2) { // single sample
    				readPairs(args[1], dao);
    			}
    			else {
    				System.out.print("Usage: InsertRecord -P [path to group dir]");
    				System.exit(1);
    			}
    		}
    		else {
    			System.out.println("Option -S or -P is missing.");
    			System.exit(1);
    		}
        } catch (Exception e) {
			e.printStackTrace();
		}
    }

/**
 * Read from either all .info from a directory or a single .info file 
 * and push all attributes to database
 */
    public static void readSingles(String str, CghubDao dao) {
		File dir = new File(str);
		if (!dir.isDirectory()){
			System.out.println("ERROR: " + str + " is not a directory.");
			System.exit(1);
		}
		// single sequence directory
		else if (isSeedDir(dir)) {
			File infoFile = new File(dir, dir.getName() + ".info");
			SeqTb seq = parseInfoFile(infoFile);
			dao.insertSeq(seq);
			recordCount = 1;
		}
		// parent folder of multiple sequences 
		else {
			File[] files = dir.listFiles();
			walkFiles(files, dao);
		}
		
		System.out.println("Total of " + recordCount + " records inserted.");
    }
    
    public static void walkFiles(File[] files, CghubDao dao) {
    	for (File file : files) {
    		if (file.isDirectory()) {
    			if (!isSeedDir(file)) {
    				walkFiles(file.listFiles(), dao);
    			}
    			else {
    				File infoFile = new File(file, file.getName() + ".info");
    				SeqTb seq = parseInfoFile(infoFile);
    				if (seq != null) {
    					try {
    						dao.insertSeq(seq);
    						
    					} catch (Exception e) {
    						System.out.println("ERROR: with " + infoFile.getAbsolutePath());
    					} finally {
    						recordCount += 1;
    					}
    				}
    			}
    		}
    	}
    }
    
    public static boolean isSeedDir(File file) {
    	File[] files = file.listFiles();
    	if (files.length > 3) {
    		return false;
    	}
    	File infoFile = new File(file, file.getName() + ".info");
    	if (!infoFile.exists()) {
    		return false;
    	}
    	for (File f : files) {
    		if (f.isDirectory()) {
    			return false;
    		}
    		if (f.getName().endsWith(".bam") || f.getName().contains(".fastq")) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
	 * Read from directory that contains tumor-normal pairs
	 * 
	 * @param dir, name of donor directory that includes two subfolders
	 */
	public static void readPairs(String path, CghubDao dao) {
		File groupdir = new File(path);
		// name of the top level directory is the name of data group
		String groupName = groupdir.getName();
		GroupTb group = new GroupTb(groupName);
		List<PairTb> pairList = new ArrayList<PairTb>();
		File[] pairDirs = groupdir.listFiles();
		// counter of sequences
		int counter = 0;
		for (File pairdir : pairDirs) {  // iterate all pair sample directories
			if (pairdir.isDirectory()) {
				File[] samples = pairdir.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File current, String name) {
						return new File(current, name).isDirectory();
					}
				});
				
				// only read the files in dirs that has two subfolders (normal, tumor)
				if (samples.length == 2) { 
					PairTb pair = new PairTb(pairdir.getName(), group);
					List<SeqTb> seqList = new ArrayList<SeqTb>();
					for (File sampDir : samples) {
						File[] files = sampDir.listFiles();
						for (File f : files) {
							String idStr = sampDir.getName();
							File infoFile = new File(f.getParent(), idStr + ".info");
							if (infoFile.exists()) {
								SeqTb seq = parseInfoFile(infoFile);
//								dao.insertSeq(seq);
								seqList.add(seq);
								counter ++;
								break;
							}
						}
					}
					if (seqList.size() != 2) {
						System.out.println(pairdir.getName() + " is not a valid pair directory.");
					}
					else {
						pair.setSeqTbs(seqList);
					}
//					dao.insertPair(pair);
					pairList.add(pair);
				}
				
			}
		}
		if (pairList == null) {
			System.out.println("No pair is found in directory " + groupdir.getAbsolutePath());
		}
		else {
			dao.insertGroup(group);
			System.out.println("Total of " + pairList.size() + " pairs " + counter + " sequences inserted.");
		}
		
	}

/**
 * Parse .info file line by line and save as CGHubData type variables.
 * Push data to database if it is valid.
 * 
 * @return Total number of records pushed to database.
 */
    public static SeqTb parseInfoFile(File file) {
        //indicator of file type
        String fileType = "";
        SeqTb seq = new SeqTb();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line = reader.readLine();
            while(line != null) {
                // strip leading and trailing spaces
                String lineStr = line.trim();

                // name of the directory that contains files for this .info
                String path = file.getParentFile().getAbsolutePath();
                
                // Set common fields
                if(lineStr.startsWith("analysis_id")) {
                   seq.setAnalysisId(lineStr.split("                  : ")[1]);                        
                }
                if(lineStr.startsWith("last_modified")) {
                    String dateStr = lineStr.split("                : ")[1].split("T")[0];
                    seq.setLastModified(formatter.parse(dateStr));
                }
                if(lineStr.startsWith("published_date")) {
                    String dateStr = lineStr.split("               : ")[1].split("T")[0];
                    seq.setPublishedDate(formatter.parse(dateStr));
                }
                if(lineStr.startsWith("center_name")) {
                    seq.setCenter(lineStr.split("                  : ")[1]);
                }
                if(lineStr.startsWith("study")) {
                    seq.setStudy(lineStr.split("                        : ")[1]);
                }
                if(lineStr.startsWith("aliquot_id")) {
                	seq.setAliquotId(lineStr.split("                   : ")[1]);
                }
                
                if(lineStr.startsWith("filename") && lineStr.endsWith(".bam")) {
                    fileType = "bam";
                    String bamName = lineStr.split("             : ")[1];
//                    File bamFile = new File(path + "/" + bamName);
//                    if(bamFile.exists()) {
//                        seq.setFilepath(bamFile.getCanonicalPath());
//                        
//                    }
//                    else {
//                    	System.out.println("File " + bamFile.getCanonicalPath() + " does not exist");
//                    }  
                    seq.setFilename(bamName);
                    seq.setSeqFormat("bam");
                }
                if(lineStr.startsWith("filename") && lineStr.endsWith(".bai")) {
                    fileType = "bai";
                }
                if(lineStr.startsWith("filename") && lineStr.indexOf(".tar") > 0) {
                    fileType = "fastq";
                    String fastqName = lineStr.split("             : ")[1];
//                    File fastqFile = new File(path + "/" + fastqName);                   
//                    if(fastqFile.exists()) {
//                        seq.setFilepath(fastqFile.getCanonicalPath());   
//                        
//                    }
//                    else {
//                    	System.out.println("File " + fastqFile.getCanonicalPath() + " does not exist");
//                    }
                    seq.setFilename(fastqName);
                    seq.setSeqFormat("fastq");
                }
                if(lineStr.startsWith("filesize")) {
                    if(fileType != "bai") {
                        seq.setFilesize((int) (Long.parseLong(lineStr.split("             : ")[1]) / 1024));
                    }
                }
                if(lineStr.startsWith("checksum")) {
                    if(fileType != "bai") {
                        seq.setChecksum(lineStr.split("             : ")[1]);
                    }
                }                
                if(lineStr.startsWith("participant_id")) { 
                	seq.setParticipantId(lineStr.split("               : ")[1]);
                }
                if(lineStr.startsWith("sample_id")) { 
                	seq.setSampleId(lineStr.split("                    : ")[1]);
                }

                if(lineStr.startsWith("platform")) { 
                	seq.setPlatform(lineStr.split("                     : ")[1]);
                }
                if(lineStr.startsWith("refassem_short_name")) { 
                	seq.setRefassem(lineStr.split("          : ")[1]);
                }
                if(lineStr.startsWith("analysis_data_uri")) {
                	seq.setDataUrl(lineStr.split("            : ")[1]);
                }
                // Set TCGA only tags
                if(lineStr.startsWith("legacy_sample_id")) { 
                	seq.setLegacyId(lineStr.split("             : ")[1]);
                	seq.setSeqSource("TCGA");                	
                }
                if(lineStr.startsWith("disease_abbr")) {   
                	seq.setDiseaseAbbr(lineStr.split("                 : ")[1]);
                }
                if(lineStr.startsWith("tss_id")) {
                	seq.setTssId(lineStr.split("                       : ")[1]);
                }
                if(lineStr.startsWith("analyte_code")) {
                	seq.setAnalyteCode(lineStr.split("                 : ")[1]);
                }
                if(lineStr.startsWith("sample_type")) {
                	seq.setSampleType(lineStr.split("                  : ")[1]); // also set normal/tumor
                }
                
                // Set ICGC only fields
                if(lineStr.startsWith("dcc_project_code")) {   
                	seq.setDccProject(lineStr.split("             : ")[1]);
                	seq.setSeqSource("ICGC");
                }
                if(lineStr.startsWith("dcc_specimen_type")) {
                	seq.setDccSpecimenType(lineStr.split("            : ")[1]);
                }
                if(lineStr.startsWith("specimen_id")) {
                	seq.setSpecimenId(lineStr.split("                  : ")[1]);  // also set normal/tumor
                }
                
                line = reader.readLine();
            }
            reader.close();
            
        }
        catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
            return null;
        }
        return seq;
    }

}
    
