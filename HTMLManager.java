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
  
}
