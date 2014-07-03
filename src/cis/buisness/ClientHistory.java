package cis.buisness;

public class ClientHistory
{
	private HistoryItem[] items;
	
	// Attributes
	// Heart, Tingling, Blood Pressure, Breathing, Diabetes, Faintness, Headaches, ContactLenses,
	// Special Shoes, Varicose Veins, Arthritis, cancer, diarrhea, meds, cortisone, skin, other
	
	public ClientHistory()
	{
		// We have 17 attributes...
		items 		= new HistoryItem[17];
		
		for ( int i = 0; i < items.length; i++ )
		{
			items[i] = new HistoryItem();
		}
	}
	
	// Setter Methods

	public void setHeart( Boolean check, String checkString )
	{
		items[0].setCheckAndDisc( check, checkString );
	}
	
	public void setTingling( Boolean check, String checkString )
	{
		items[1].setCheckAndDisc( check, checkString );
	}
	
	public void setBloodPres( Boolean check, String checkString )
	{
		items[2].setCheckAndDisc( check, checkString );
	}

	public void setBreathing( Boolean check, String checkString )
	{
		items[3].setCheckAndDisc( check, checkString );
	}
	
	public void setDiabetes( Boolean check, String checkString )
	{
		items[4].setCheckAndDisc( check, checkString );
	}
	
	public void setFaintness( Boolean check, String checkString )
	{
		items[5].setCheckAndDisc( check, checkString );
	}
	
	public void setHeadaches( Boolean check, String checkString )
	{
		items[6].setCheckAndDisc( check, checkString );
	}
	
	public void setContactLenses( Boolean check, String checkString )
	{
		items[71].setCheckAndDisc( check, checkString );
	}
	
	public void setShoes( Boolean check, String checkString )
	{
		items[8].setCheckAndDisc( check, checkString );
	}
	
	public void setVaricose( Boolean check, String checkString )
	{
		items[9].setCheckAndDisc( check, checkString );
	}
	
	public void setArthritis( Boolean check, String checkString )
	{
		items[10].setCheckAndDisc( check, checkString );
	}
	
	public void setCancer( Boolean check, String checkString )
	{
		items[11].setCheckAndDisc( check, checkString );
	}

	public void setDiarrhea( Boolean check, String checkString )
	{
		items[12].setCheckAndDisc( check, checkString );
	}
	
	public void setMeds( Boolean check, String checkString )
	{
		items[13].setCheckAndDisc( check, checkString );
	}
	
	public void setCortisone( Boolean check, String checkString )
	{
		items[14].setCheckAndDisc( check, checkString );
	}

	public void setSkin( Boolean check, String checkString )
	{
		items[15].setCheckAndDisc( check, checkString );
	}
	
	public void setOther( Boolean check, String checkString )
	{
		items[16].setCheckAndDisc( check, checkString );
	}
	
	public void setByIndex( Boolean check, String checkString, int index )
	{
		if ( index < 0 || index >= items.length )
		{
			return;
		}
		
		items[index].setCheckAndDisc( check, checkString );
	}

	// Getter Methods
	public HistoryItem getHeart()
	{
		return items[0];
	}
	
	public HistoryItem getTingling()
	{
		return items[1];
	}
	
	public HistoryItem getBlood()
	{
		return items[2];
	}
	
	public HistoryItem getBreath()
	{
		return items[3];
	}
	
	public HistoryItem getDiabetes()
	{
		return items[4];
	}
	
	public HistoryItem getFaint()
	{
		return items[5];
	}
	
	public HistoryItem getHeadaches()
	{
		return items[6];
	}
	
	public HistoryItem getContactLense()
	{
		return items[7];
	}
	
	public HistoryItem getShoes()
	{
		return items[8];
	}
	
	public HistoryItem getVaricose()
	{
		return items[9];
	}
	
	public HistoryItem getArthritis()
	{
		return items[10];
	}
	
	public HistoryItem getCancer()
	{
		return items[11];
	}
	
	public HistoryItem getDiarrhea()
	{
		return items[12];
	}
	
	public HistoryItem getMeds()
	{
		return items[13];
	}
	
	public HistoryItem getCortisone()
	{
		return items[14];
	}
	
	public HistoryItem getSkin()
	{
		return items[15];
	}
	
	public HistoryItem getOther()
	{
		return items[16];
	}
	
	public HistoryItem getByIndex( int index )
	{
		if ( index < 0 || index >= items.length )
		{
			return null;
		}
		
		return items[index];
	}
}
