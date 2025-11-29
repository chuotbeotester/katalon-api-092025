<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>profile</name>
   <tag></tag>
   <elementGuidId>6e43b3a3-077a-4fdb-9b82-3ffcda0f7b41</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <authorizationRequest>
      <authorizationInfo>
         <entry>
            <key>bearerToken</key>
            <value>${GlobalVariable.token}</value>
         </entry>
      </authorizationInfo>
      <authorizationType>Bearer</authorizationType>
   </authorizationRequest>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>-1</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;name\&quot;: \&quot;${name}\&quot;,\n  \&quot;email\&quot;: \&quot;${email}\&quot;,\n  \&quot;password\&quot;: \&quot;\&quot;,\n  \&quot;password_old\&quot;: \&quot;${password}\&quot;,\n  \&quot;avatarUrl\&quot;: \&quot;${avatarUrl}\&quot;,\n  \&quot;phone\&quot;: \&quot;${phone}\&quot;,\n  \&quot;address\&quot;: \&quot;${address}\&quot;,\n  \&quot;config\&quot;: null\n}&quot;,
  &quot;contentType&quot;: &quot;text/plain&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>ee1522f2-f92e-4fe7-ac2b-8e0c54343f27</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>Bearer ${GlobalVariable.token}</value>
      <webElementGuid>582efd7e-b48f-40eb-8ee8-2f3b3262cde9</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>10.3.1</katalonVersion>
   <maxResponseSize>-1</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>PATCH</restRequestMethod>
   <restUrl>${GlobalVariable.url}/api/profile</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>-1</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>GlobalVariable.name</defaultValue>
      <description></description>
      <id>67ed1a0e-9776-4463-955e-b0dc46591956</id>
      <masked>false</masked>
      <name>name</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.email</defaultValue>
      <description></description>
      <id>f627ca23-3d09-4db7-873a-3f59a78d5f39</id>
      <masked>false</masked>
      <name>email</name>
   </variables>
   <variables>
      <defaultValue>'QuynhNguyen'</defaultValue>
      <description></description>
      <id>db99242a-a457-4dd0-977c-409d16397445</id>
      <masked>false</masked>
      <name>password</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.password</defaultValue>
      <description></description>
      <id>7570bdcd-f379-4afa-8fbd-d6c0fc996c6b</id>
      <masked>false</masked>
      <name>password_old</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.avatarUrl</defaultValue>
      <description></description>
      <id>88b4c350-d379-427f-9fc1-4e5ee2559455</id>
      <masked>false</masked>
      <name>avatarUrl</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>ea184345-ce1d-4e66-961d-f574d493efd8</id>
      <masked>false</masked>
      <name>phone</name>
   </variables>
   <variables>
      <defaultValue>''</defaultValue>
      <description></description>
      <id>c6f6c4a2-2ebe-48ca-8104-d610e7ccf7ce</id>
      <masked>false</masked>
      <name>address</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
