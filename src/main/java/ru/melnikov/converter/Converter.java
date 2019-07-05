package ru.melnikov.converter;


import com.google.common.base.Strings;

class Converter {

    String parse(String ucid) throws UcidFormatException {
        if (ucid == null) throw new UcidFormatException("ucid is null");
        ucid = ucid.trim();
        int length = ucid.length();
        if (length < 16) throw new UcidFormatException("INVALID UCID=" + ucid);

        long g1 = Long.parseLong(ucid.substring(0, length - 15));

        if (g1 < 1L) throw new UcidFormatException("INVALID UCID=" + ucid);

        long g2 = Long.parseLong(ucid.substring(length - 15, length - 10));
        long g3 = Long.parseLong(ucid.substring(length - 10, length));

        if ((g1 > 32767L) || (g2 > 99999L) || (g3 > 9999999999L)) throw new UcidFormatException("INVALID UCID=" + ucid);

        long rez = (g1 << 48 | g2 << 32 | g3);

        return rez + "";
    }

    String parseACR(String acr) throws UcidFormatException {
        if (acr == null) throw new UcidFormatException("ACR Call ID is null");
        //if (acr.length() < 16) throw new UcidFormatException("INVALID ACR Call ID = " + acr);
        String sRez;
        acr = acr.trim();
        long rez = Long.parseLong(acr);
        long c1 = (rez >> 48);
        long c2 = (rez - (c1 << 48)) >> 32;
        long c3 = rez - (c1 << 48) - (c2 << 32);

        sRez =  String.valueOf(c1) + String.valueOf(c2) + String.valueOf(c3);

        return Strings.padStart(sRez,20,'0');
    }


}