import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  
  public void HTMLManager(Queue<HTMLTag> html) {
     if(html == null) {
        throw new IllegalArgumentException("The html is null");
     }
     tags = new LinkedList<>();
     while(!html.isEmpty()) {
        tags.add(html.remove());
     }
  }
  
  public Queue<HTMLTag> getTags() {
     return tags;
  }
  
  public String toString() {
     String result = "";
     while(!tags.isEmpty()) {
        HTMLTag tag = tags.remove();
        result += tag.toString() + " ";   
     }
     return result;
  }
  
}
