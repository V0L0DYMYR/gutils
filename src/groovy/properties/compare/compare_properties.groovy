package groovy.properties.compare

file_one = 'D:\\repo\\git\\mygithub\\gutils\\src\\groovy\\properties\\update\\message_en.properties'
file_two = 'D:\\repo\\git\\mygithub\\gutils\\src\\groovy\\properties\\update\\message_ua.properties'

def file_1 = new File(file_one)
def props_1 = new Properties()
props_1.load(file_1.newDataInputStream())

def file_2 = new File(file_two)
def props_2 = new Properties()
props_2.load(file_2.newDataInputStream())

p1_minus_p2 =  props_1 - props_2
p2_minus_p1 =  props_2 - props_1

p1_only = p1_minus_p2.keySet() - p2_minus_p1.keySet()
if(!p1_only.isEmpty()) {
    println '\nOnly in ' + file_1.getName()
    p1_only.each{printf("%-80s|%-80s\n", [it, props_1.get(it)])}
}

p2_only = p2_minus_p1.keySet() - p1_minus_p2.keySet()
if(!p2_only.isEmpty()){
    println '\nOnly in ' + file_2.getName()
    p2_only.each{printf("%-80s|%-80s\n", [it, props_2.get(it)])}
}
chenged_keys = p1_minus_p2.keySet() - p1_only
if(!chenged_keys.isEmpty()){
    println '\nModified'
    printf("%-80s|%-80s|%-80s\n", ['', file_1.getName(), file_2.getName()])
    println '-'*80 + '+'+'-'*80 + '+' + '-'*80
    chenged_keys.eachWithIndex { key, int i ->
        change = [key, props_1.get(key), props_2.get(key)]
        printf("%-80s|%-80s|%-80s\n", change)
    }
}