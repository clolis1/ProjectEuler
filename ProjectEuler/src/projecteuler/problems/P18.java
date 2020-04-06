package projecteuler.problems;

public class P18 implements Problem{
    public P18() {
        super();
    }
    
    public void execute() {
        System.out.println("By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.\n" + 
        "\n" + 
        "   3\n" + 
        "  7 4\n" + 
        " 2 4 6\n" + 
        "8 5 9 3\n" + 
        "\n" + 
        "That is, 3 + 7 + 4 + 9 = 23.\n" + 
        "\n" + 
        "Find the maximum total from top to bottom of the triangle below:\n" + 
        "\n" + 
        "              75\n" + 
        "             95 64\n" + 
        "            17 47 82\n" + 
        "           18 35 87 10\n" + 
        "          20 04 82 47 65\n" + 
        "         19 01 23 75 03 34\n" + 
        "        88 02 77 73 07 63 67\n" + 
        "       99 65 04 28 06 16 70 92\n" + 
        "      41 41 26 56 83 40 80 70 33\n" + 
        "     41 48 72 33 47 32 37 16 94 29\n" + 
        "    53 71 44 65 25 43 91 52 97 51 14\n" + 
        "   70 11 33 28 77 73 17 78 39 68 17 57\n" + 
        "  91 71 52 38 17 14 91 43 58 50 27 29 48\n" + 
        " 63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" + 
        "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23\n" + 
        "\n" + 
        "NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route." +
        "However, Problem 67, is the same challenge with a triangle containing one-hundred rows;" +
        "it cannot be solved by brute force, and requires a clever method! ;o)");
        
        int grid[][] = {    {75}, 
                            {95, 64},
                            {17, 47, 82},
                            {18, 35, 87, 10},
                            {20, 4, 82, 47, 65},
                            {19, 1, 23, 75, 3, 34},
                            {88, 2, 77, 73, 7, 63, 67},
                            {99, 65, 4, 28, 6, 16, 70, 92},
                            {41, 41, 26, 56, 83, 40, 80, 70, 33},
                            {41, 48, 72, 33, 47, 32, 37, 16, 94, 29},
                            {53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14},
                            {70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57},
                            {91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48},
                            {63, 66, 4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31},
                            {4, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 4, 23}    };
        
        int[] max_sums = new int[grid[grid.length - 1].length]; // int array with same number of spots as bottom layer of triangle.
        
        max_sums[0] = grid[0][0];
        
        // Iterate through each row of the triangle. Keep track of the maximum value that can be reached at each node in a tier.
        for (int i = 1; i < grid.length; i++) {
            
            max_sums[i] = max_sums[i-1]; // Rightmost node is node one level higher + right child.
            
            // Determine highest value path per node for non-edge nodes.
            for (int j = grid[i].length - 2; j > 0; j--) {
                max_sums[j] = grid[i][j] + ((max_sums[j] > max_sums[j-1]) ? max_sums[j] : max_sums[j-1]);
            }
            
            // Calculate highest value for left-only and right-only paths.
            max_sums[0] += grid[i][0];
            max_sums[i] += grid[i][grid[i].length - 1];
        }
        
        // Determine greatest vale path.
        int max = 0;
        for (int i : max_sums) {
            if (i > max) max = i;
        }
        
        System.out.println("\nAnswer: " + max);
    }
}
