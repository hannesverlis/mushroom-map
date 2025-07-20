// Seene ala piirid
export const bounds = [
    [57.9, 23.5], // SW
    [59.5, 28.29] // NE
];

export function addAreaRectangle(map) {
    L.rectangle(bounds, {
        color: "#3388ff",
        weight: 2,
        fillOpacity: 0.15,
        fillColor: "#3388ff",
        dashArray: "4"
    }).addTo(map);
}

export function addAreaLabels(map) {
    // Eemaldan nurkade labelid, alles jäävad ainult servade omad
    // Horisontaalsed servad
    L.marker([59.5, (23.5 + 28.29) / 2], {
        icon: L.divIcon({
            className: 'coord-label',
            html: `<span style="background:rgba(255,255,255,0.7);padding:2px 6px;border-radius:4px;font-size:13px;color:#333;">lat 59.5</span>`,
            iconSize: [60, 20],
            iconAnchor: [30, 0]
        }),
        interactive: false
    }).addTo(map);
    L.marker([57.9, (23.5 + 28.29) / 2], {
        icon: L.divIcon({
            className: 'coord-label',
            html: `<span style="background:rgba(255,255,255,0.7);padding:2px 6px;border-radius:4px;font-size:13px;color:#333;">lat 57.9</span>`,
            iconSize: [60, 20],
            iconAnchor: [30, 0]
        }),
        interactive: false
    }).addTo(map);
    // Vertikaalsed servad
    L.marker([(57.9 + 59.5) / 2, 23.5], {
        icon: L.divIcon({
            className: 'coord-label',
            html: `<span style="background:rgba(255,255,255,0.7);padding:2px 6px;border-radius:4px;font-size:13px;color:#333;">lon 23.5</span>`,
            iconSize: [60, 20],
            iconAnchor: [30, 0]
        }),
        interactive: false
    }).addTo(map);
    L.marker([(57.9 + 59.5) / 2, 28.29], {
        icon: L.divIcon({
            className: 'coord-label',
            html: `<span style="background:rgba(255,255,255,0.7);padding:2px 6px;border-radius:4px;font-size:13px;color:#333;">lon 28.29</span>`,
            iconSize: [60, 20],
            iconAnchor: [30, 0]
        }),
        interactive: false
    }).addTo(map);
}

export function showToast(message) {
    const toast = document.getElementById('toast');
    toast.innerHTML = message;
    toast.style.display = 'block';
    setTimeout(() => {
        toast.style.display = 'none';
    }, 3500);
}

export function isInsideEstonia(lon, lat) {
    return lon >= 23.5 && lon <= 28.29 && lat >= 57.9 && lat <= 59.5;
} 