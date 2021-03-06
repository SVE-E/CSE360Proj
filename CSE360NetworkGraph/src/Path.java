
public class Path {
	private String path;
	private int length;
	
	public Path() {
		path="";
		length=0;
	}
	
	public Path(Path other) {
		path = other.getPath();
		length= other.getLength();
	}
	
	public void addNextNode(Node next) {
		if(path==""){
			path+=next.getName();
		}else {
			path+= "->" + next.getName();
		}
		length+=next.getDuration();
	}
	
	public String getPath() {
		return path;
	}
	
	public int getLength() {
		return length;
	}
	
	public String toString() {
		return "Path: " + path + " Length: " + length;
	}
}
