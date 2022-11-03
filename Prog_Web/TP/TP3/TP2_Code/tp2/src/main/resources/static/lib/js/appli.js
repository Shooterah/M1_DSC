
let app = Vue.createApp({
    template: '<vege-app></vege-app>',
});

app.component('vege-app', {
    props: {titre: String, taux: {default: 1.55}},
    template: "<h1 :title='leTITRE'>{{titre}}</h1> <br> Il y a {{veges.length}} éléments</h1> <br> <ul> <li v-for='vege in veges' :title='enCAD(vege.price)' :style={background:vege.color}> {{vege.name}} coûte <span>{{vege.price}}</span> €</li> </ul> <br> <button @click='ajoutTest()'>Ajout Test</button>",
    data: () => ({
        titre: 'Légumes',
        veges : [
            {name: 'Tomato', color: 'red', price: 2.50},
            {name: 'Carrot', color: 'orange', price: 1.50},
            {name: 'Potato', color: 'brown', price: 1.00},
            {name: 'Cucumber', color: 'green', price: 2.00},
            {name: 'Onion', color: 'white', price: 1.50},
            {name: 'Garlic', color: 'white', price: 1.50},
            {name: 'Lettuce', color: 'green', price: 2.00},
        ]
    }),
    methods: {
        enCAD: function (enEuros) {
            return enEuros * this.taux;
        },
        ajoutTest: function () {
            this.veges.push({name: 'Test', color: 'pink', price: 1.00});
        },
        loadData: async function () {
            let res = await fetch('/api/vegetables') // hard coded :(, not HATEOAS
            let body = await res.json()
            this.veges = body._embedded.vegetables
        }
    },
    computed: {
        leTITRE: function () {
            return this.titre.toUpperCase();
        }
    },
    mounted: () => {
        this.loadData();
    }
});

let vm = app.mount('#container');