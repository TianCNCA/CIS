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

  public void add(Soap theSoap) {
	  visits.add(theSoap);
  }

  public Soap last(){
    return visits.get(visits.size());
  }
  
  public ArrayList<Soap> getSoaps(){
	  return visits;
  }

}
