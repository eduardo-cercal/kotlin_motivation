package com.devmasterteam.motivation.repository

import br.com.eduardocercal.motivation.infra.MotivationConstants
import kotlin.random.Random

data class Phrase(val description: String, val category: Int)

class Mock {

    private val all = MotivationConstants.CATEGORY.ALL
    private val emotions = MotivationConstants.CATEGORY.EMOTIONS
    private val sunny = MotivationConstants.CATEGORY.SUNNY

    private val listPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", emotions),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", emotions),
        Phrase("Quando está mais escuro, vemos mais estrelas!", emotions),
        Phrase(
            "Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.",
            emotions
        ),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", emotions),
        Phrase(
            "O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?",
            emotions
        ),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", sunny),
        Phrase("Você perde todas as chances que você não aproveita.", sunny),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", sunny),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", sunny),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", sunny),
        Phrase("Se você acredita, faz toda a diferença.", sunny),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", sunny)
    )

    // Obtém frase aleatória de acordo com o filtro
    fun getPhrase(value: Int): String {
        val filtered = listPhrases.filter { (it.category == value || value == all) }

        // Número aleatório de 0 ao tamanho da lista retornada do filtro
        val rand = Random.nextInt(filtered.size)

        // Retorna string
        return filtered[rand].description
    }

}