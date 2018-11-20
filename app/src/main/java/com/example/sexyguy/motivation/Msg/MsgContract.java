package com.example.sexyguy.motivation.Msg;

import android.content.Context;
import android.graphics.Bitmap;

public interface MsgContract {

    interface view{
        void setCheerUp(String msg, Bitmap img);
        Context getContextForModel();
    }

    interface  presenterForView{

    }

    interface presenterForModel{
        void setCheerUp(String msg, Bitmap img);
        Context getContextForModel();
    }

    interface model{

    }
}
