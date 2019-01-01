
var storage = {
    put(key, value) {
        localStorage.setItem(key, JSON.stringify(value));
    },

    get(key) {
        let valStr = localStorage.getItem(key);
        //console.log(valStr);
        return JSON.parse(valStr);
    },

    remove(key) {
        localStorage.removeItem(key);
    },
     
    clear(){
        localStorage.clear();
    }
};

export default storage;