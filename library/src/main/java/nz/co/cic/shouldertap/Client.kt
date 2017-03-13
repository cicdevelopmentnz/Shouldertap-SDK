package nz.co.cic.shouldertap

import android.content.Context
import nz.co.cic.ble.scanner.Scanner
import nz.co.cic.wifi.Wifi

/**
 * Created by dipshit on 12/03/17.
 */

class Client(private val mContext: Context){

    private var scanner: Scanner? = null
    private var wifi: Wifi? = null
    init{
        this.wifi = Wifi(mContext)
        this.scanner = Scanner(mContext)
    }

    fun start(){
        scanner?.startFiltered("Shouldertap-Gateway", arrayOf("Gateway-Name", "Gateway-Password"))?.subscribe({
            gatewayInfo ->
            
            println(gatewayInfo.toString())
            var gatewayName = gatewayInfo.get("Gateway-Name")
            var gatewayPass = gatewayInfo.get("Gateway-Password")

            println("Gateway info: " + gatewayName + " " + gatewayPass)

            if(gatewayName != null) {
                wifi?.client?.connect(gatewayName as String, gatewayPass as String)?.subscribe({
                    connect ->

                }, {
                    err ->
                    println("Failed to connect")
                }, {
                    scanner?.stop()
                })
            }

        })
    }

    fun stop(){
        scanner?.stop()
        wifi?.client?.disconnect()?.subscribe({})
    }
}
