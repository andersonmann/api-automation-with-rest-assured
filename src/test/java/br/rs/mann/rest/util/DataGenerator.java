package br.rs.mann.rest.util;

import java.util.Random;

/**
 * @author anderson.mann
 *
 */

public class DataGenerator {

    /**
     * This method generated a random username
     *
     * @return
     */
    public String createUserName() {
        String prefix = "user test ";
        double random = Math.random() * 101;
        String userName = prefix.concat(String.valueOf(random));
        return userName;
    }

    /**
     * This method generated a random email
     *
     * @return
     */

    public String createEmail() {
        String prefix = "emailtest";
        String provider = "@gmail.com";
        double random = Math.random() * 101;
        return prefix.concat(String.valueOf(random).concat(provider));
    }

    /**
     * This method generated a valid CPF
     *
     * @return
     */
    public String createCpf() {
        int n = 9;
        int n1 = random(n);
        int n2 = random(n);
        int n3 = random(n);
        int n4 = random(n);
        int n5 = random(n);
        int n6 = random(n);
        int n7 = random(n);
        int n8 = random(n);
        int n9 = random(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;
        d1 = 11 - (mod(d1, 11));
        if (d1 >= 10) d1 = 0;
        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;
        d2 = 11 - (mod(d2, 11));
        String newCpf = null;
        if (d2 >= 10) d2 = 0;
        newCpf = "".concat(String.valueOf(n1)).concat(String.valueOf(n2)).concat(String.valueOf(n3))
                .concat(String.valueOf(n4).concat(String.valueOf(n5).concat(String.valueOf(n6))
                        .concat(String.valueOf(n7).concat(String.valueOf(n8).concat(String.valueOf(n9)
                                .concat(String.valueOf(d1).concat(String.valueOf(d2))))))));
        return newCpf;
    }

    /**
     * Auxiliary method
     *
     * @param n
     * @return
     */
    private int random(int n) {
        Random r = new Random();
        int rand = r.nextInt(n);
        return rand;
    }

    /**
     * Auxiliary method
     *
     * @param dividendo
     * @param divisor
     * @return
     */
    private int mod(int dividendo, int divisor) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    public static void main(String[] args) {
        DataGenerator gerador = new DataGenerator();
        String cpf = gerador.createCpf();
        System.out.printf("CPF: %s", cpf);
    }
}