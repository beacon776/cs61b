package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: Qing He
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
            if (side == Side.SOUTH) {
            board.setViewingPerspective(Side.SOUTH);}
            if (side == Side.WEST) {
                board.setViewingPerspective(Side.WEST);
            }
            if (side == Side.EAST) {
                board.setViewingPerspective(Side.EAST);
            }
            int cn=0;
            for (int i = 0; i <= 3; i++) {
                Tile a = board.tile(i, 3);
                Tile b = board.tile(i, 2);
                Tile c = board.tile(i, 1);
                Tile d = board.tile(i, 0);
                if (board.tile(i, 3) == null && board.tile(i, 2) == null && board.tile(i, 1) == null && board.tile(i, 0) == null) {//0 0 0 0
                }
                else if(board.tile(i, 3) != null && board.tile(i, 2) == null && board.tile(i, 1) == null && board.tile(i, 0) == null){//2 0 0 0

                }
                else if(board.tile(i, 3) == null && board.tile(i, 2) != null && board.tile(i, 1) == null && board.tile(i, 0) == null){//0 2 0 0
                    board.move(i,3,b);
                    cn++;}
                else if(board.tile(i, 3) == null && board.tile(i, 2) == null && board.tile(i, 1) != null && board.tile(i, 0) == null){//0 0 2 0
                    board.move(i,3,c);
                    cn++;
                }
                else if(board.tile(i, 3) == null && board.tile(i, 2) == null && board.tile(i, 1) == null && board.tile(i, 0) != null){//0 0 0 2
                    board.move(i,3,d);
                    cn++;
                }
                else if(board.tile(i, 3) != null && board.tile(i, 2) != null && board.tile(i, 1) == null && board.tile(i, 0) == null){
                    if(a.value()!=b.value()){//4 2 0 0

                    }
                    else if(a.value()==b.value()){//2 2 0 0
                        score+=2*a.value();
                        board.move(i,3,b);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) != null && board.tile(i, 2) == null && board.tile(i, 1) != null && board.tile(i, 0) == null){
                    if(a.value()!=c.value()){//4 0 2 0
                        board.move(i,2,c);
                        cn++;
                    }
                    else if(a.value()==c.value()){//2 0 2 0
                        score+=2*a.value();
                        board.move(i,3,c);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) != null && board.tile(i, 2) == null && board.tile(i, 1) == null && board.tile(i, 0) != null){
                    if(a.value()!=d.value()){//4 0 0 2
                        board.move(i,2,d);
                        cn++;
                    }
                    else if(a.value()==d.value()){//2 0 0 2
                        score+=2*a.value();
                        board.move(i,3,d);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) == null && board.tile(i, 2) != null && board.tile(i, 1) != null && board.tile(i, 0) == null){
                    if(b.value()!=c.value()){//0 4 2 0
                        board.move(i,3,b);
                        board.move(i,2,c);
                        cn++;
                    }
                    else if(b.value()==c.value()){//0 2 2 0
                        score+=2*b.value();
                        board.move(i,3,b);
                        board.move(i,3,c);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) == null && board.tile(i, 2) != null && board.tile(i, 1) == null && board.tile(i, 0) != null){
                    if(b.value()!=d.value()){//0 4 0 2
                        board.move(i,3,b);
                        board.move(i,2,d);
                        cn++;
                    }
                    else if(b.value()==d.value()){//0 2 0 2
                        score+=2*b.value();
                        board.move(i,3,b);
                        board.move(i,3,d);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) == null && board.tile(i, 2) == null && board.tile(i, 1) != null && board.tile(i, 0) != null){
                    if(c.value()!=d.value()){//0 0 4 2
                        board.move(i,3,c);
                        board.move(i,2,d);
                        cn++;
                    }
                    else if(c.value()==d.value()){//0 0 2 2
                        score+=2*c.value();
                        board.move(i,3,c);
                        board.move(i,3,d);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) != null && board.tile(i, 2) != null && board.tile(i, 1) != null && board.tile(i, 0) == null){
                    if(a.value()!=b.value()&&b.value()!=c.value()){//8 4 2 0

                    }
                    else if(a.value()==b.value()){//2 2 2 0 or 4 4 2 0
                        score+=2*a.value();
                        board.move(i,3,b);
                        board.move(i,2,c);
                        cn++;
                    }
                    else if(a.value()!=b.value()&&b.value()==c.value()){//4 2 2 0
                        score+=2*b.value();
                        board.move(i,2,c);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) != null && board.tile(i, 2) == null && board.tile(i, 1) != null && board.tile(i, 0) != null){
                    if(a.value()!=c.value()&&c.value()!=d.value()){//8 0 4 2
                        board.move(i,2,c);
                        board.move(i,1,d);
                        cn++;
                    }
                    else if(a.value()==c.value()){//2 0 2 2 or 4 0 4 2
                        score+=2*a.value();
                        board.move(i,3,c);
                        board.move(i,2,d);
                        cn++;
                    }
                    else if(a.value()!=c.value()&&c.value()==d.value()){//4 0 2 2
                        score+=2*c.value();
                        board.move(i,2,c);
                        board.move(i,2,d);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) != null && board.tile(i, 2) != null && board.tile(i, 1) == null && board.tile(i, 0) != null){
                    if(a.value()!=b.value()&&b.value()!=d.value()){//8 4 0 2
                        board.move(i,1,d);
                        cn++;
                    }
                    else if(a.value()==b.value()){//2 2 0 2 or 4 4 0 2
                        score+=2*a.value();
                        board.move(i,3,b);
                        board.move(i,2,d);
                        cn++;
                    }
                    else if(a.value()!=b.value()&&b.value()==d.value()){//4 2 0 2
                        score+=2*b.value();
                        board.move(i,2,d);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) == null && board.tile(i, 2) != null && board.tile(i, 1) != null && board.tile(i, 0) != null){
                    if(b.value()!=c.value()&&c.value()!=d.value()){//0 8 4 2
                        board.move(i,3,b);
                        board.move(i,2,c);
                        board.move(i,1,d);
                        cn++;
                    }
                    else if(b.value()==c.value()){//0 2 2 2 or 0 4 4 2
                        score+=2*b.value();
                        board.move(i,3,b);
                        board.move(i,3,c);
                        board.move(i,2,d);
                        cn++;
                    }
                    else if(b.value()!=c.value()&&c.value()==d.value()){//0 4 2 2
                        score+=2*c.value();
                        board.move(i,3,b);
                        board.move(i,2,c);
                        board.move(i,2,d);
                        cn++;
                    }
                }
                else if(board.tile(i, 3) != null && board.tile(i, 2) != null && board.tile(i, 1) != null && board.tile(i, 0) != null){
                    if(a.value()!=b.value()&&b.value()!=c.value()&&c.value()!=d.value()){//16 8 4 2

                    }
                    else if(a.value()==b.value()&&c.value()!=d.value()){//2 2 4 2 or 4 4 4 2
                        score+=2*a.value();
                        board.move(i,3,b);
                        board.move(i,2,c);
                        board.move(i,1,d);
                        cn++;
                    }
                    else if(a.value()!=b.value()&&c.value()==d.value()){//8 4 2 2
                        score+=2*c.value();
                        board.move(i,1,d);
                        cn++;
                    }
                    else if(a.value()!=b.value()&&b.value()==c.value()){//8 4 4 2
                        score+=2*b.value();
                        board.move(i,2,c);
                        board.move(i,1,d);
                        cn++;
                    }
                    else if(a.value()==b.value()&&c.value()==d.value()){//4 4 2 2
                        score+=2*a.value()+2*c.value();
                        board.move(i,3,b);
                        board.move(i,2,c);
                        board.move(i,2,d);
                        cn++;
                    }
                }
                if(cn==0) changed=false;
                else changed=true;
            }
            board.setViewingPerspective(Side.NORTH);
            checkGameOver();
            if (changed) {
                setChanged();
            }
            return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        int count=0;
        for(int i=0;i<b.size();i++){
            for(int j=0;j<b.size();j++){
                if(b.tile(i,j)==null) return true;
            }
        }
       return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        for(int i=0;i<b.size();i++){
            for(int j=0;j<b.size();j++){
                if(b.tile(i,j)!=null){           //不为空的话才能去调用tile的值
               Tile t = b.tile(i,j);
                 if(t.value()==MAX_PIECE)   return true;}
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {//写的没问题，就该在相邻的两个数不是0的情况下验证是否存在相邻两个数相等
        for(int i=0;i<b.size();i++){
            for(int j=0;j<b.size();j++){
                if(b.tile(i,j)==null) return true;
            }
        }
        for(int i=0;i< b.size()-1;i++){//左右 注意这里的i，j严格对应直角坐标系中的x和y，这和数组的遍历不太一样哦，因为tile的设计就是严格按照建系来的
            for(int j=0;j< b.size();j++){
                if(b.tile(i,j)!=null&&b.tile(i+1,j)!=null){
                    Tile c=b.tile(i,j);
                    Tile d =b.tile(i+1,j);
                    if(c.value()==d.value()) return true;
                }
            }
        }
        for(int i=0;i< b.size();i++){//上下
            for(int j=0;j< b.size()-1;j++){
                if(b.tile(i,j)!=null&&b.tile(i,j+1)!=null){
                    Tile c=b.tile(i,j);
                    Tile d =b.tile(i,j+1);
                    if(c.value()==d.value()) return true;
                }
            }
        }
        return false;
    }


    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
