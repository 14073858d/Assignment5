package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	String name,type,data;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		
		//Delete the lines below and add your code here
		if(getAttribute==false){
			try{
				name=br.readLine();
				type=br.readLine();
				data=br.readLine();
				tuple=new Tuple(name,type,data);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				getAttribute=true;
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
		}else if(getAttribute==true){
			try {
				data = br.readLine();
				if (data!=null){
					Attribute attribute;
					attribute = new Attribute();
					tuple=new Tuple(name,type,data);
					tuple.setAttributeName();
					tuple.setAttributeType();
					tuple.setAttributeValue();
				}
				else
					return null;
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return tuple;	
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	
}