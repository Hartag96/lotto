import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Losowanie {
    public int index;
    public LocalDate date;
    public int[] lotto;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    Losowanie(2String line){
        this.lotto = new int[6];
        String [] fields = line.split("\t", -1);

        this.index = Integer.parseInt(fields[0]);
        this.date = LocalDate.parse(fields[1], formatter);

        this.lotto[0] = Integer.parseInt(fields[2]);
        this.lotto[1] = Integer.parseInt(fields[3]);
        this.lotto[2] = Integer.parseInt(fields[4]);
        this.lotto[3] = Integer.parseInt(fields[5]);
        this.lotto[4] = Integer.parseInt(fields[6]);
        this.lotto[5] = Integer.parseInt(fields[7]);
    }

    public String getNumbers (){
        return Arrays.stream(this.lotto).mapToObj(String::valueOf).collect(Collectors.joining(", "));
    }
}
