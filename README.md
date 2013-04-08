gutils
======

collection of utils/scripts
<ol>
  <li>
  <b>update_properties.groovy</b><br/>
  <i>>groovy update_properties.groovy release_1.0.properties</i>
  <pre>
    # release_1.0.properties
    # other comments
    
    #> message_en.properties
    header.text = Text for message_en.properties only
    
    #> message_ua.properties
    header.text = Текст лише для файлу message_ua.properties
    
    #>  message_ua.properties message_en.properties
    common.property = some common property
  </pre>
  </li>

  <li>
    <b>compare_properties.groovy</b><br/>
    <i>>groovy compare_properties.groovy message_en.properties message_ua.properties</i>
    <pre>
    Only in message_en.properties
    prop.only.en         |only here

    Only in message_ua.properties
    prop.only.ua         |only here

    Modified
                         |message_en.properties    |message_ua.properties
    ---------------------+-------------------------+----------------------
    common.modified.prop |en                       |ua
    </pre>
  </li>
</ol>
