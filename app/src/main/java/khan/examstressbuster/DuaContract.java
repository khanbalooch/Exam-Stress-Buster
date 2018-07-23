package khan.examstressbuster;

import android.provider.BaseColumns;

/**
 * Created by Khan on 27-Feb-17.
 */

public class DuaContract
{
    private DuaContract(){}

    public static final class DuaEntry implements BaseColumns
    {
        public static final String DUA_TABLE = "DUA";
        public static final String DUA_NAME = "NAME";
        public static final String COL_DUA_TEXT_ARABIC = "TEXT_ARABIC";
        public static final String COL_DUA_TEXT_ENGLISH  = "TEXT_ENGLISH";
        public static final String COL_DUA_TEXT_URDU = "TEXT_URDU";
        public static final String COL_DUA_REFERENCE = "REFERENCE";
    }
}