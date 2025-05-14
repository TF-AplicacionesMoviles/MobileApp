//se agrega implementación y un string de la dependencia"
//implementation("com.squareup.retrofit2:retrofit:2.11.0")
//implementation("com.squareup.retrofit2:converter-gson:2.11.0")

en libs.versions.toml

eliminar version de convertergson y ponerle la de retrofit

- PODER USAR APIS DE INTERNET
    ir a manifest -> AndroidManifest
   <uses-permission android:name="android.permission.INTERNET"/>
   (poner dentro de etiqueta manifest y antes de application )

Project Structure:
- viewmodel-compose(viewmodel)
- androidx.compose.runtime(mutablestateof)
- androidx.navigation.compose (rooteo)
    

PORTS (dev: interferencias en el backend, poner en puerto 8081):

res/xml/network_security_config.xml:

<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">10.0.2.2</domain>
    </domain-config>
</network-security-config>

EN ANDROIDMANIFEST.XML
<application
android:name=".MyApp"
android:networkSecurityConfig="@xml/network_security_config"
...>

