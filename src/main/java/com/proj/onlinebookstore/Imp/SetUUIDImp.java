package com.proj.onlinebookstore.Imp;

import org.springframework.stereotype.Component;
import java.util.UUID;

import com.proj.onlinebookstore.Fun.SetUUID;

@Component
public class SetUUIDImp implements SetUUID {
    @Override
    public String getUUID() {
        String uuid = UUID.randomUUID().toString();
        String[] idd = uuid.split("-");
        return idd[0] + idd[1] + idd[2];
    }
}
