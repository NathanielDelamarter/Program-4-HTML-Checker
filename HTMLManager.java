import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  
  public HTMLManager(Queue<HTMLTag> html) {
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
     for(HTMLTag tag : tags) {
        result += tag.toString().trim() + " ";   
     }
     return result;
  }
  
  public void fixHTML() {
     int size = tags.size();
     Stack<HTMLTag> result = new Stack<>();
     for(int i = 0; i < size; i++) {
        HTMLTag tag = tags.remove();
        if(tag.isOpening()) {
           result.push(tag);
           tags.add(tag);
        } else if (tag.isSelfClosing()) {
           tags.add(tag);
        } else if (tag.isClosing() && result.isEmpty()) {
          //nothing to do here in this case
        } else if (tag.isClosing() && !result.peek().equals(tag)) {
           HTMLTag add = result.pop();
           HTMLTag correct = add.getMatching();
           tags.add(correct);
        } else if (tag.isClosing() && result.peek().equals(tag)) {
          result.pop();
          tags.add(tag);
        }
     }
     while(!result.isEmpty()) {
        HTMLTag add = result.pop();
        HTMLTag correct = add.getMatching();
        tags.add(correct);        
     }
  }
  
}

