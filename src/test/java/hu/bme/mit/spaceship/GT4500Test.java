package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  TorpedoStore primaryTorpedoStore = mock(TorpedoStore.class);
  TorpedoStore secondaryTorpedoStore = mock(TorpedoStore.class);

  @BeforeEach
  public void init(){
    
    this.ship = new GT4500(primaryTorpedoStore, secondaryTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    // Verify

    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primaryTorpedoStore.isEmpty()).thenReturn(false);
    when(primaryTorpedoStore.fire(1)).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    // Verify
    verify(primaryTorpedoStore, times(1)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void firstEmpty_Single_Success()
  {
     // Arrange
     when(primaryTorpedoStore.isEmpty()).thenReturn(true);
     when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
     when(secondaryTorpedoStore.fire(1)).thenReturn(true);
 
     // Act
     boolean result = ship.fireTorpedo(FiringMode.SINGLE);
 
     // Assert
     assertEquals(true, result);
 
     // Verify
     verify(primaryTorpedoStore, times(0)).fire(1);
     verify(secondaryTorpedoStore, times(1)).fire(1);
  
  }

  @Test
  public void Empty_Single_Fail()
  {
     // Arrange
     when(primaryTorpedoStore.isEmpty()).thenReturn(true);
     when(secondaryTorpedoStore.isEmpty()).thenReturn(true);
 
     // Act
     boolean result = ship.fireTorpedo(FiringMode.SINGLE);
 
     // Assert
     assertEquals(false, result);
 
     // Verify
 
     verify(primaryTorpedoStore, times(0)).fire(1);
     verify(secondaryTorpedoStore, times(0)).fire(1);
  
  }

  @Test
  public void Empty_ALL_Fail()
  {
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    assertEquals(false, result);

    verify(primaryTorpedoStore, times(0)).fire(1);
    verify(secondaryTorpedoStore, times(0)).fire(1);

  }

  @Test
  public void Empty_Primary_ALL_Success()
  {
    when(primaryTorpedoStore.isEmpty()).thenReturn(true);
    when(secondaryTorpedoStore.isEmpty()).thenReturn(false);
    when(secondaryTorpedoStore.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.ALL);

    assertEquals(true, result);

    verify(primaryTorpedoStore, times(0)).fire(1);
    verify(secondaryTorpedoStore, times(1)).fire(1);

  }


}
