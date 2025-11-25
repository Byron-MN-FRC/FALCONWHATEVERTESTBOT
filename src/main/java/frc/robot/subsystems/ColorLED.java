package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;

public class ColorLED {
    private AddressableLEDBuffer m_ledBuffer;
    public AddressableLED m_led;
    private int m_rainbowFirstPixelHue;

    public void initLED(int port, int length) {
        m_led = new AddressableLED(port);
        m_ledBuffer = new AddressableLEDBuffer(length);
        //144 lights on big strand
        //75 seems to be max amount before something explodes
        //8 lights on small strand
        m_led.setLength(m_ledBuffer.getLength());
    
        m_led.setData(m_ledBuffer);
    
        m_led.start();
    }
    public void setLED(int red, int green, int blue) {
        
    }
}
