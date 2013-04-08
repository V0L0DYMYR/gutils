package groovy.properties.update

files = [
        'message_ua.properties': 'D:\\repo\\git\\mygithub\\gutils\\src\\groovy\\properties\\update\\message_en.properties',
        'message_en.properties': 'D:\\repo\\git\\mygithub\\gutils\\src\\groovy\\properties\\update\\message_ua.properties'
]

//main
update(args[0])

def update(String fileName) {
    file = new File(fileName).text.split('\n').collect { if (isNotComment(it)) return it } - null
    check_files()
    changes = [:]
    collect_changes()
    write('last update from ' + fileName)
}

def write(String comment) {
    changes.each {
        def file_2_write = new File(it.key)
        def properties = new Properties()
        properties.load(file_2_write.newDataInputStream())
        it.value.each { prop ->
            properties.put(prop.key, prop.value)
        }
        properties.store(file_2_write.newWriter('UTF-8'), comment)
    }
}

def collect_changes() {
    def current_files = [] as Set
    file.each {
        if (it.startsWith('#>')) {
            current_files = it.substring(2).split()
            return;
        }
        //else
        def prop = it.split('=')*.trim()
        current_files.each {
            def props = changes[it]
            if (!props) {
                props = [:]
                changes[it] = props
            }
            props[prop[0]] = prop[1]
        }
    }
}

def check_files() {
    files_2_change = [] as Set
    file.each {
        if (it.startsWith('#>'))
            files_2_change.addAll(it.substring(2).split())
    } - null

    files_2_change.each { prop_file ->
        def path = files[prop_file]
        if (!path) throw new RuntimeException('Unknown file:' + prop_file)
        if (!new File(path).exists()) throw new RuntimeException('Unknown path:' + path + ' for file:' + prop_file)
    }
}

def isNotComment(String line) {
    return !line.trim().isEmpty() && (!line.startsWith("#") || line.startsWith("#>")) ? true : false;
}