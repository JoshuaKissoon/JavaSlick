package com.joshuakissoon.slick;

import com.joshuakissoon.slick.module.ModuleManager;
import com.joshuakissoon.slick.module.SlickModule;

/**
 * An interface to handle bootstrapping a system
 *
 * @author Joshua Kissoon
 * @since 20150913
 */
public interface Bootstrap
{

    /**
     * Method to bootup the system
     *
     * @return {Boolean} Whether the operation was successful
     */
    public boolean bootup();

    public ModuleManager getModuleManager();

    /**
     * Method to load the system modules
     *
     * @return {Boolean} Whether the operation was successful
     */
    public default boolean loadModules()
    {
        for (SlickModule mod : this.getModuleManager().getModules())
        {
            mod.bootstrap();
        }

        return true;
    }
}
