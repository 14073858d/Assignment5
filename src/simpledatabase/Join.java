package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuplesleft;
	ArrayList<Tuple> tuplesright;
	boolean a=true;
	boolean b=true;
	int right=0;
	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesleft = new ArrayList<Tuple>();
		tuplesright=new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		while(a){
			Tuple tuple=leftChild.next();
			if(tuple!=null){
				tuplesleft.add(tuple);
			}
			else{
				a=false;
			}
		}
		while(b){
			Tuple tuple2=rightChild.next();
			if(tuple2!=null){
				tuplesright.add(tuple2);
			}
			else{
				b=false;
			}
		}
		//Delete the lines below and add your code here
		
		int left =0;
		Tuple lefttuple=tuplesleft.get(left);
		
		if (right<tuplesright.size()){
			Tuple righttuple=tuplesright.get(right);
			right++;
			while(left<tuplesleft.size()){
				if (lefttuple!=null && righttuple!=null){
					for(int i=0;i<lefttuple.getAttributeList().size();i++){
						for(int j=0;j<righttuple.getAttributeList().size();j++){
							if(lefttuple.getAttributeName(i).equals(righttuple.getAttributeName(j))){
								if (lefttuple.getAttributeValue(i).equals(righttuple.getAttributeValue(j))){
									newAttributeList=new ArrayList<Attribute>();
									newAttributeList.addAll(lefttuple.getAttributeList());
									newAttributeList.addAll(righttuple.getAttributeList());
									return new Tuple(newAttributeList);
								}
							}
						}
					}
				}
			left++;
			lefttuple=tuplesleft.get(left);
			}
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}