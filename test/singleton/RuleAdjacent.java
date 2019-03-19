package test.singleton;

import java.util.Random;

public class RuleAdjacent{
    public RuleAdjacent() {

    }

    public int[][] rules5(int ruleSerial) {
        int [][] rules = new int[2][16];
        switch (ruleSerial) {
            case 2:
                // Value of status
                rules[0] = new int[]{0,1,1,1,1,1,1,0,0,0,0,1,0,0,1,1};
                // Direction of next cell
                rules[1] = new int[]{2,3,2,7,8,8,6,3,0,1,0,5,5,6,7,1};
                break;
            case 3:
                // Value of status
                rules[0] = new int[]{1,1,1,0,0,1,1,0,1,0,0,1,0,0,1,1};
                // Direction of next cell
                rules[1] = new int[]{6,3,7,8,5,8,6,3,0,1,0,2,5,2,7,1};
                break;
            case 4:
                // Value of status
                rules[0] = new int[]{1,0,0,1,1,1,1,1,1,1,1,0,1,0,1,1};
                // Direction of next cell
                rules[1] = new int[]{5,6,8,7,7,7,3,6,0,8,1,8,1,0,7,5};
                break;
            case 5:
                // Value of status
                rules[0] = new int[]{1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0};
                // Direction of next cell
                rules[1] = new int[]{6,8,6,8,6,8,6,8,6,8,6,0,2,0,2,0};
                break;
            case 999:
                // Value of status
                rules[0] = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0};
                // Direction of next cell
                rules[1] = new int[]{6,8,6,8,6,8,6,8,6,8,6,0,2,0,2,0};
                break;
            default:
            case 1:
                // Value of status
                rules[0] = new int[]{0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1};
                // Direction of next cell
                rules[1] = new int[]{0,1,2,3,5,6,7,8,0,1,2,3,5,6,7,8};
                break;
        }
        return rules;
    }

    private int[][] rules5() {
        int[][] rules = new int[2][16];
        // Value of status
        rules[0] = new int[]{0,1,1,0,1,0,1,0,1,0,0,0,1,0,1,1};
        // Direction of next cell
        rules[1] = new int[]{0,1,2,3,5,6,7,8,0,1,2,3,5,6,7,8};
        return rules;
    }

    public int[] rules9(int ruleSerial) {
        int [] rules = null;
        return rules;
    }
    public int[] rules13(int ruleSerial) {
        int [] rules = null;
        return rules;
    }
    public int[] rules25(int ruleSerial) {
        int [] rules = null;
        return rules;
    }



    public void applyRule(int ruleType, int ruleSerial) {
        Random r = new Random();
        int low = 0;
        int high = 8;
        int result = r.nextInt(high-low) + low;
        GridSquareCell gsc = GridSquareCell.getInstance();
        switch (ruleType) {
            case 9:{
                break;
            }
            default:
            case 5:{
                int [][] rule = rules5(ruleSerial);
                int status;
                CellSquare cell = GridSquareCell.getActiveCell();
                int sum = cell.up().getStatus()+cell.down().getStatus()+cell.left().getStatus()+cell.right().getStatus();
                // All 16 possible outcomes
                if (sum==0) status = 0;
                else if (sum == 1) {
                    if (cell.up().getStatus() == 1) status = 1;
                    else if (cell.down().getStatus() == 1) status = 2;
                    else if (cell.left().getStatus() == 1) status = 3;
                    else status = 4;
                } else if (sum == 2) {
                    if (cell.up().getStatus() == cell.down().getStatus()) {
                        if (cell.up().getStatus() == 0) status = 5;
                        else status = 6;
                    } else if (cell.up().getStatus() == cell.left().getStatus()) {
                        if (cell.up().getStatus() == 0) status = 7;
                        else status = 8;
                    } else{
                        if (cell.up().getStatus() == 0) status = 9;
                        else status = 10;
                    }
                } else if (sum == 3) {
                    if (cell.up().getStatus() == 0) status = 11;
                    else if (cell.down().getStatus() == 0) status = 12;
                    else if (cell.left().getStatus() == 0) status = 13;
                    else status = 14;
                } else status = 15;
                cell.setStatus(rule[0][status]);
                CellSquare [] neighbor = gsc.getNeighbor(cell,9);
                if (ruleSerial != 999) gsc.setActiveCell(neighbor[rule[1][status]].gridCoord[0],neighbor[rule[1][status]].gridCoord[1]);
                else gsc.setActiveCell(neighbor[result].gridCoord[0],neighbor[result].gridCoord[1]);
                break;
            }

        }

    }
    // Default test case
    public void applyRule() {
        applyRule(5,1);
    }
}
