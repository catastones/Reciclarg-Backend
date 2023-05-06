
let encuesta;
let motivo = [];
let paradero=[];
let pregunta=[];
let recicla=[];
let desechos=[];
let motivoLabel=[];
let motivoData=[];
//http://localhost:8080/encuesta/listencuesta
const xhttp = new XMLHttpRequest();
xhttp.open('GET','./encuestas.json',true);
xhttp.send();

xhttp.onreadystatechange = function(){
    if(this.readyState == 4 && this.status == 200){

        encuesta = JSON.parse(this.responseText);
        console.log(encuesta);
        for (let item of encuesta){
        motivo.push(item.motivo.tipoMotivo) ;
        desechos.push(item.desecho.tipoDesecho);
        paradero.push(item.paradero.tipoParadero);
        pregunta.push(item.pregunta.pregunta);
        recicla.push(item.recicla.tipoRecicla);
        }
        grafico('canvas',motivo,'Motivo');
        grafico('canvas1',desechos,'Desechos');
        grafico('canvas2',paradero,'Paradero');
        grafico('canvas3',pregunta,'Pregunta');
        grafico('canvas4',recicla,'Recicla');

    }
}

function elementCount (arr, element){
    return arr.filter((CurrentElement)=> CurrentElement == element).length;
}
function grafico(idCanvas, entidad, titulo){
    
    let LabelEntidad = [];
    let dataEntidad = [];
    entidad.forEach(element => {
            if(!LabelEntidad.includes(element)){LabelEntidad.push(element)}
        });
        
        const ctx = document.getElementById(idCanvas);
       
        for(var item of LabelEntidad ){
            dataEntidad.push(elementCount(entidad,item));
        }
        new Chart(ctx, {
          type: 'bar',
          data: {
            labels: LabelEntidad,
            datasets: [{
              label: titulo,
              data: dataEntidad,
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });
}

