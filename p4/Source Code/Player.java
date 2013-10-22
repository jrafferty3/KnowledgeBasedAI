import java.io.*;
import java.util.ArrayList;

public interface Player{
    int getMove(int[] board, ArrayList<String> trace) throws IOException;
}
