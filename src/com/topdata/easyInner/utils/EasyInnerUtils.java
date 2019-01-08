package com.topdata.easyInner.utils;

import com.topdata.easyInner.enumeradores.Enumeradores;

/**
 *
 * @author juliano.ezequiel
 */
public class EasyInnerUtils {

    private static long Timeout;
    /**
     * Converte de Byte para HEX
     *
     * @param P
     * @return
     */
    public static StringBuffer convertArrayByteToHex(byte[] P) {
        int iVlrAsc = 0;
        StringBuffer sSaida = new StringBuffer();
        int i = 0;

        for (i = 0; i <= P.length - 1; i++) {
            iVlrAsc = (P[i] < 0 ? (P[i] + 256) : P[i]);

            sSaida.append(completaString(Integer.toHexString(iVlrAsc), 2, "0"));
        }

        return (sSaida);
    }

    /**
     * Completa string de acordo com os parâmetros enviados
     *
     * @param var1
     * @param Len
     * @param complemento
     * @return
     */
    public static String completaString(String var1, int Len, String complemento) {
        while (var1.length() < Len) {
            var1 = complemento + var1;
        }
        return (var1);
    }

    /**
     * Seta Timeout Bio
     */
    public static void setarTimeoutBio() {
        Timeout = System.currentTimeMillis() + 7000;
    }
    
    /**
     * Espera Resposta Bio
     *
     * @param Retorno
     * @return
     * @throws InterruptedException
     */
    public static boolean isEsperaRespostaBio(int Retorno) throws InterruptedException {
        Thread.sleep(300);
        return ((Retorno != Enumeradores.RET_COMANDO_OK) && (System.currentTimeMillis() <= Timeout));
    }
    
    /**
     * Remove zeros a esquerda
     * @param valor
     * @return 
     */
    public static String remZeroEsquerda(String valor) {
        boolean blnNum = false;
        String str = new String();
        for (int i = 0; i < valor.length(); i++) {
            if (!"0".equals(valor.substring(i, i + 1))) {
                blnNum = true;
            }
            if (blnNum) {
                str += valor.substring(i, i + 1);
            }
        }
        return str;
    }
    
    public native long GetTickCount();

    /**
     * Métodos Auxiliares para recuperar os segundos da máquina e para Sleep em
     * Milisegundos..
     *
     * @param ms
     * @return
     */
    public native long Sleep(long ms);
}
