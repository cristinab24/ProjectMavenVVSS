package org.vvss.project.Validator;

import org.vvss.project.Domain.Teme;

public class TemeValidator implements Validator<Teme> {
    @Override
    public String validate(Teme t) {
        String m;
        m = "";
        if (t.getID() == null || t.getID().equals("") || t.getID() < 1)
            m = m + "\nID invalid";
        if(t.getDescriere()==null || t.getDescriere().equals(""))
            m = m + "\nDescriere invalida";
        if (t.getDeadline() > 14 || t.getDeadline() < 1 || t.getDeadline() < t.getSapt_primire())
            m = m + "\nDeadline invalid";
        if (t.getSapt_primire() > 14 || t.getSapt_primire() < 1)
            m = m + "\nSaptamana in care tema a fost primita este invalida";
        return m;
    }
}
