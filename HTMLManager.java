import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  
  public HTMLManager(Queue<HTMLTag> html) {
     if(html == null) {//check if the queue is null
        throw new IllegalArgumentException("The html is null");
     }
     tags = new LinkedList<>();
     while(!html.isEmpty()) {
        tags.add(html.remove());//add all the tags from the html to private queue "tags"
     }
  }
  //return the tags field
  public Queue<HTMLTag> getTags() {
     return tags;
  }
  
  public String toString() {
     String result = "";
     for(HTMLTag tag : tags) {//take each elemnts from the queue "tags" without discarding it and add it to the string separetly
        result += tag.toString().trim() + " ";   
     }
     return result;
  }
  //fixing the incorrect HTMLTag Queue
  public void fixHTML() {
     int size = tags.size(); // save the size of the Q so that we can use it to iterate through
     Stack<HTMLTag> result = new Stack<>(); // new stack to hold all of the opening values
     for(int i = 0; i < size; i++) { // for the size of the Q
        HTMLTag tag = tags.remove(); //remove the individual tag from the Q
        if(tag.isOpening()) { // if its an opening value add it to the stack and back to the Q
           result.push(tag);
           tags.add(tag);
        } else if (tag.isSelfClosing()) { // if its selfClosing value then just add back to the Q
           tags.add(tag);
        } else if (tag.isClosing() && result.isEmpty()) {// if its if it's  a closing value and there has been no opening value do nothing
          //nothing to do here in this case, just remove from the Q which we already did
        } else if (tag.isClosing() && !result.peek().equals(tag.getMatching())) {//if the tag is closing and the stack doesn't contain it's matching opening value
           //then we add the correct version to the Q and remove the opening value from the stack
           HTMLTag add = result.pop();
           HTMLTag correct = add.getMatching();
           tags.add(correct);
        } else if (tag.isClosing() && result.peek().equals(tag.getMatching())) {//if the tag is closing and the stack does contain it's matching opening value
          //then we simply add it back to the Q and remove from the stack          
          result.pop();
          tags.add(tag);
        }
     }
     while(!result.isEmpty()) { // if there's anything left in the stack add it's correct ending value to the end of the Q
        HTMLTag add = result.pop();
        HTMLTag correct = add.getMatching();
        tags.add(correct);        
     }
  }
  
}

/*
 
  ----jGRASP exec: java HTMLChecker
 ===============================
 Processing tests/test1.html...
 ===============================
 HTML: <b> <i> <br /> </b> </i> 
 Checking HTML for errors...
 HTML after fix: <b> <i> <br /> </i> </b> 
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test2.html...
 ===============================
 HTML: <a> <a> <a> </a> 
 Checking HTML for errors...
 HTML after fix: <a> <a> <a> </a> </a> </a> 
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test3.html...
 ===============================
 HTML: <br /> </p> </p> 
 Checking HTML for errors...
 HTML after fix: <br /> 
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test4.html...
 ===============================
 HTML: <div> <div> <ul> <li> </li> <li> </li> <li> </ul> </div> 
 Checking HTML for errors...
 HTML after fix: <div> <div> <ul> <li> </li> <li> </li> <li> </li> </ul> </div> </div> 
 ----> Result matches Expected Output!
 
 ===============================
 Processing tests/test5.html...
 ===============================
 HTML: <div> <h1> </h1> <div> <img /> <p> <br /> <br /> <br /> </div> </div> </table> 
 Checking HTML for errors...
 HTML after fix: <div> <h1> </h1> <div> <img /> <p> <br /> <br /> <br /> </p> </div> </div> 
 ----> Result matches Expected Output!
 
 ===============================
         All tests passed!
 ===============================
 
  ----jGRASP: Operation complete.
*/

