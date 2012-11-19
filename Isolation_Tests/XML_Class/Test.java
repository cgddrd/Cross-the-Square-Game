public class Test {

	public static void main (String[] args) throws Exception{
	
		XML xmlClass = new XML();
		
		//xmlClass.setScoreData("This,is,working!");
		String testString = "This is working";
		
		System.out.println("Print to xml: This is working");
		
		xmlClass.addToXML(testString);
		xmlClass.readXML();
		
	}
}
	
	
