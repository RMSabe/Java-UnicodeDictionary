/*
This class/object ScreenLayout is "useless".
The object itself has no usage, but its instance memory address will be useful to manually adjust container and components.
*/

import java.awt.*;

public class ScreenLayout implements LayoutManager
{
	@Override
	public void addLayoutComponent(String name, Component comp) {}

	@Override
	public void removeLayoutComponent(Component comp) {}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		return null;
	}

	@Override
	public void layoutContainer(Container parent) {}
}
