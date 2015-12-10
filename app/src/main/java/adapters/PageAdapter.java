package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import imerosa.apptriunfadores.ListSolicitudesPrestamosFragment;

/**
 * Created by mompi3p on 10/12/2015.
 */
public class PageAdapter extends FragmentPagerAdapter {
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
        {
            return new  ListSolicitudesPrestamosFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }
}
