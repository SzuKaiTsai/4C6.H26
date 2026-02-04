package ca.qc.cstj.cleanarchitecture.data

import ca.qc.cstj.cleanarchitecture.models.MeditationSession

object MockData {
    val meditationSessions = listOf(
        MeditationSession("Respiration Fondatrice", 5, listOf("Débutant", "Ancrage")),
        MeditationSession("Focus Matinal", 10, listOf("Énergie", "Productivité")),
        MeditationSession("Relâchement Musculaire Profond", 20, listOf("Sommeil", "Corps")),
        MeditationSession("Gestion du Stress Aigu", 3, listOf("Urgence", "Calme")),
        MeditationSession("Marche en Pleine Conscience", 15, listOf("Extérieur", "Mouvement")),
        MeditationSession("Gratitude et Bienveillance", 12, listOf("Émotion", "Positivité")),
        MeditationSession("Scanner Corporel du Soir", 25, listOf("Sommeil", "Relaxation")),
        MeditationSession("Visualisation de la Réussite", 10, listOf("Mental", "Confiance")),
        MeditationSession("Silence Intérieur", 30, listOf("Avancé", "Silence")),
        MeditationSession("Pause Déjeuner Zen avec Alain Martel", 7, listOf("Travail", "Déconnexion"))
    )
}