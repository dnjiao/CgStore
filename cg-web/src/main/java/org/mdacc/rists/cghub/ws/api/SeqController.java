package org.mdacc.rists.cghub.ws.api;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mdacc.rists.cghub.model.SeqTb;
import org.mdacc.rists.cghub.ws.service.SeqService;
import org.mdacc.rists.ws.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/")
public class SeqController {
	private static BigInteger nextId;
	private static Map<BigInteger, Greeting> greetingMap;
	
	private static Greeting save(Greeting greeting) {
		if(greetingMap == null) {
			greetingMap = new HashMap<BigInteger, Greeting>();
			nextId = BigInteger.ONE;
		}
		greeting.setId(nextId);
		nextId = nextId.add(BigInteger.ONE);
		greetingMap.put(greeting.getId(), greeting);
		return greeting;
	}

	static {
		Greeting g1 = new Greeting();
		g1.setText("Hello World!");
		save(g1);
		
		Greeting g2 = new Greeting();
		g1.setText("Hola Mundo!");
		save(g2);
		
	}
	@RequestMapping(
			value = "/api/greetings", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		Collection<Greeting> greetings = greetingMap.values();
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
		
	}
	@Autowired
	private SeqService seqService;
	@RequestMapping(
			value = "/api/seqs", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SeqTb>> getSeqs() {
		List<SeqTb> seqs = seqService.findAll();
		
		return new ResponseEntity<List<SeqTb>>(seqs, HttpStatus.OK);
	}
	
	@RequestMapping( 
			value = "/api/seqs/{analysis_id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeqTb> getSeqByAnalysisId(@PathVariable("analysis_id") String analysis_id) {
		SeqTb seq = seqService.findByAnalysisId(analysis_id);
		return new ResponseEntity(seq, HttpStatus.OK);
	}
}
