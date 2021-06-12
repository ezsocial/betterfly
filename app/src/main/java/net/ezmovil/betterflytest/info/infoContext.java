package net.ezmovil.betterflytest.info;

import android.content.Context;

public final class infoContext {
    public static Context _context;

    public Context get_context() {
        return _context;
    }

    public void set_context(Context _context) {
        infoContext._context = _context;
    }
}