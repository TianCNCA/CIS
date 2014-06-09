package cis.buisness;

import java.util.*;

public class SoapBox {
  protected ArrayList <Soap> visits;
  
  public SoapBox(){
    visits = new ArrayList<Soap>();
  }
  
  public void add(Date date, String info){
    Soap newSoap = new Soap(date, info);
    visits.add(newSoap);
  }
  
  public Soap last(){
    return visits.get(visits.size());
  }
  
  public Soap[] getSoaps(){
	  Soap soaps[] = new Soap[visits.size()];
	  for(int i=0; i<visits.size(); i++){
		  soaps[i] = visits.get(i);
	  }
	  return soaps;
  }
}
