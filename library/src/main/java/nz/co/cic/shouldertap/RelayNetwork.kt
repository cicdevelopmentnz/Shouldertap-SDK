package nz.co.cic.shouldertap

import android.net.wifi.p2p.WifiP2pGroup

/**
 * Created by dipshit on 12/03/17.
 */

data class RelayNetwork(val wifiP2pGroup: WifiP2pGroup){

    var ssid: String? = null
    var pass: String? = null
    init{
        this.ssid = wifiP2pGroup.networkName
        this.pass = wifiP2pGroup.passphrase
    }

    fun toMap():HashMap<String, String>{
        var map = HashMap<String, String>()
        map.put("Gateway-Name", this.ssid!!)
        map.put("Gateway-Password", this.pass!!)
        return map
    }
}
