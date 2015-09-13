package com.joshuakissoon.slick.module;

import java.util.ArrayList;

/**
 * A module manager class to manage modules
 *
 * @author Joshua Kissoon
 * @since 20150913
 */
public class ModuleManager
{

    private final ArrayList<SlickModule> modules;

    
    {
        modules = new ArrayList<>();
    }

    public ModuleManager()
    {
    }

    /**
     * Register all modules in the system with module manager
     */
    private void registerModule(SlickModule module)
    {
        this.modules.add(module);
    }

    public ArrayList<SlickModule> getModules()
    {
        return this.modules;
    }
}
