let historyList = [];

function validateCard() {

    const input = document.getElementById("cardNumber");
    const card = input.value.trim();
    const result = document.getElementById("result");

    if (!/^\d+$/.test(card)) {
        result.innerHTML = "❌ Card number must contain only digits";
        input.value = "";
        return;
    }

    if (card.length < 13 || card.length > 19) {
        result.innerHTML = "❌ Invalid card length";
        addHistory(card + " → INVALID LENGTH");
        input.value = "";
        return;
    }

    let sum = 0;

    for (let i = card.length - 2; i >= 0; i -= 2) {
        let digit = parseInt(card.charAt(i)) * 2;

        if (digit > 9) {
            digit -= 9;
        }

        sum += digit;
    }

    for (let i = card.length - 1; i >= 0; i -= 2) {
        sum += parseInt(card.charAt(i));
    }

    let cardType = "UNKNOWN";

    if (card.startsWith("4")) {
        cardType = "VISA";
    }
    else if (card.startsWith("34") || card.startsWith("37")) {
        cardType = "AMERICAN EXPRESS";
    }
    else {
        let prefix = parseInt(card.substring(0, 2));

        if (prefix >= 51 && prefix <= 55) {
            cardType = "MASTERCARD";
        }
    }

    if (sum % 10 === 0) {

        result.innerHTML =
            `✅ Valid Card <br> Card Type: ${cardType}`;

        addHistory(card + " → VALID → " + cardType);

    } else {

        result.innerHTML = "❌ Invalid Card";

        addHistory(card + " → INVALID");
    }

    // ✅ ALWAYS clear input after validation
    input.value = "";
}

function addHistory(text) {

    historyList.push(text);

    const history = document.getElementById("history");

    const li = document.createElement("li");

    li.textContent = text;

    history.appendChild(li);
}

function clearHistory() {

    historyList = [];

    document.getElementById("history").innerHTML = "";

    document.getElementById("result").innerHTML = "";

    document.getElementById("cardNumber").value = "";
}