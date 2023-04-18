package com.example.pru2.PATD;

import androidx.core.util.PatternsCompat;

import com.example.pru2.MyData.MyInfo;
import com.example.pru2.Registro;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class PATD {

    public static final String TAG = "Que ganitas tengo de morirme jijij";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static byte[] createSha1( String text )
    {
        MessageDigest messageDigest = null;
        byte[] bytes = null;
        byte[] bytesResult = null;
        try
        {
            messageDigest = MessageDigest.getInstance("SHA-1");
            bytes = text.getBytes("iso-8859-1");
            messageDigest.update(bytes, 0, bytes.length);
            bytesResult = messageDigest.digest();
            return bytesResult;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytesToHex(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static boolean validarEmail(String email){
        boolean fl;
        if(email.isEmpty()){
            fl=false;
        }else{
            if(PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
                fl=true;
            }else{
                fl=false;
            }
        }
        return fl;
    }

    public static boolean usuarios(List<MyInfo> list, String usu, String correo){
        boolean fl = false;
        for(MyInfo informacion : list){
            if(informacion.getUser().equals(usu)|| informacion.getCorreo().equals(correo)){
                fl=true;
            }
        }
        return fl;
    }

    public static void fillInfo(MyInfo info){
        info.setUser(Registro.usu);
        String pass = Registro.password;
        info.setNombre(Registro.nom);
        info.setContrasena(pass);
        info.setNumero(Registro.tel);
        info.setCorreo(Registro.email);
        info.setEdad(Registro.ed);

    }
}
