package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	int index=0;
	int j=0;
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@SuppressWarnings("null")
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		int v =0;
		if(v==0){
		Tuple tuple=child.next();
		while(tuple!=null){
			tuplesResult.add(tuple);
			tuple=child.next();
		}
		for(int i=0;i<tuplesResult.get(0).getAttributeList().size();i++){//attribute in tuple	
			if(tuplesResult.get(0).getAttributeName(i).equals(orderPredicate)){
				index=i;
			}
		}
		while(j<= tuplesResult.size()-1){
			int k=j;
			int h;
			for(h=j+1;h<tuplesResult.size();h++){
				if(tuplesResult.get(k).getAttributeValue(index).toString().compareTo(tuplesResult.get(h).getAttributeValue(index).toString())>0){
					k=h;
				}	
			}
			newAttributeList = tuplesResult.get(k).getAttributeList();
			Tuple temptuple=tuplesResult.get(k);
			tuplesResult.set(k, tuplesResult.get(j));
			tuplesResult.set(j, temptuple);
			j++;
			return new Tuple(newAttributeList);
		}
		v=1;
		}
		return null;
	}
			
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}