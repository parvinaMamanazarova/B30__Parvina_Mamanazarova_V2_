import java.util.HashSet;
import java.util.Set;

public class Task4 {

    public String stringArray(Set<String> set1){
        Set<String> set2 = new HashSet<>();
        Set<String> res = new HashSet<>();

        for (String s : set2) {
            if(!set1.equals(set2)) {
                set1.add(set2.toString());
            }
        }
        for (String s : res) {
            if (!set2.equals(set1) && !set1.equals(res)) {
                set1.add(res.toString());
            }
        }
        return res.toString();
    }
}
