package cis.buisness;

import java.util.*;

public class SoapBox 
{
  private ArrayList<Soap>   visits;
  private String 			clientName;
  
  public SoapBox( String clientName )
  {
    visits = new ArrayList<Soap>();
    this.clientName = clientName;
  }
  
  public void add(Date date, String info)
  {
    Soap newSoap = new Soap(date, info);
    visits.add(newSoap);
  }
  
  public String getClientName()
  {
	  return clientName;
  }
  
  public void setClientName( String clientName )
  {
	  this.clientName = clientName;
  }
  
  public Soap last()
  {
    return visits.get(visits.size() - 1);
  }
  
  public Soap getSoapByIndex( int i )
  {
	  return visits.get( i );
  }
  
  public ArrayList<Soap> getSoaps()
  {
	  return visits;
  }
  
  public int numSoaps()
  {
	  return visits.size();
  }
}
