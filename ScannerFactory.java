package LinkedInventoryManagement.Common;

import java.util.Scanner;

/**
 * ScannerFactory
 */
public abstract class ScannerFactory {

    private static Scanner _scannerObject; 
    
    public static Scanner GetScannerInstance()
    {
        if(_scannerObject == null)
        {
            _scannerObject = new Scanner(System.in); 
        }

        return _scannerObject;
    }
    
    public static void CloseScannerInstance()
    {
        _scannerObject.close();
        _scannerObject = null; 
    } 
}