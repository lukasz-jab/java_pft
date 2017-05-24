package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import org.testng.Assert;
import org.testng.annotations.Test;
import net.webservicex.GeoIPService;
/**
 * Created by luk on 2017-05-24.
 */
public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("212.244.155.126");
        Assert.assertEquals(geoIP.getCountryCode(),"POL");
    }
}
