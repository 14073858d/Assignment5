package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;
	ArrayList<Attribute> newAttributeList;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();
		newAttributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		Tuple tuple=child.next();
		if(child.from.equals(whereTablePredicate)){
			while(tuple!=null){
				for(int i=0;i<tuple.getAttributeList().size();i++){
					String a=new String(""+tuple.getAttributeValue(i));
					if(tuple.getAttributeName(i).equals(whereAttributePredicate)&&a.equals(whereValuePredicate)){
						newAttributeList = new ArrayList<Attribute>();
						newAttributeList.addAll(tuple.getAttributeList());
						return new Tuple(newAttributeList);
					}
				}
				tuple = child.next();
			}
			return null;	
		}else{
			return tuple;
		}
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}
	
	public static void main(String[] args){
		 String selectText;
	     String fromText;
	     String joinText;
	     String whereText;
	     String orderText;
	     selectText = "Name";
	        fromText = "Student";
	        joinText = "CourseEnroll";
	        whereText = "CourseEnroll.courseID=\"COMP2021\"";
	        orderText = "";
	        Operator table = new Table("CourseEnroll");
	    	Operator selection = new Selection(table,"CourseEnroll","courseID","\"COMP2021\"");
	    	Tuple tuple = selection.next();
	    	while(tuple != null){
	    		for(int i = 0;i <tuple.getAttributeList().size();i++ ){
		    		System.out.print(tuple.getAttributeList().get(i).getAttributeName());
					System.out.print(" "+tuple.getAttributeList().get(i).getAttributeType().type.toString());
					System.out.print(" "+tuple.getAttributeList().get(i).getAttributeValue());
					System.out.println();
		    	}
	    		tuple = selection.next();
	    	}
	}

	
}