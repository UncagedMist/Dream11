package kk.techbytecare.dream11.Helper;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import java.lang.reflect.Field;

public class BottomNavigationViewHelper {

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);

            if(menuView.getChildCount() < 5)
            {
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);

                    item.setShiftingMode(false);

                    item.setChecked(item.getItemData().isChecked());
                }
            }
        }
        catch (NoSuchFieldException e) {
            Log.e("Helper", "Unable to get shift mode field", e);
        }
        catch (IllegalAccessException e) {
            Log.e("Helper", "Unable to change value of shift mode", e);
        }
    }

}
