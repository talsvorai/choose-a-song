// __tests__/song.test.js

const { showSong } = require('../index'); // Adjust path to where your showSong function is

// Mock data to simulate your songsData
const songsData = {
    "songs": [
        {
            "title": "Imagine",
            "artist": "John Lennon",
            "lyrics": [
                "Imagine there's no heaven",
                "It's easy if you try",
                "No hell below us",
                "Above us, only sky"
            ]
        },
        {
            "title": "Stairway to Heaven",
            "artist": "Led Zeppelin",
            "lyrics": [
                "There's a lady who's sure all that glitters is gold",
                "And she's buying a stairway to Heaven"
            ]
        }
    ]
};

describe('showSong function', () => {
    it('should correctly display the song lyrics for Imagine', () => {
        const songTitle = "Imagine";
        const song = songsData.songs.find(song => song.title === songTitle);
        
        const songDetailsDiv = { innerHTML: '', style: {} }; // Mocking DOM element
        showSong(songTitle, songDetailsDiv); // Pass mock DOM element

        expect(songDetailsDiv.innerHTML).toContain("Imagine by John Lennon");
        expect(songDetailsDiv.innerHTML).toContain("Imagine there's no heaven");
    });

    it('should return "Song not found" for a song that does not exist', () => {
        const songTitle = "Nonexistent Song";
        const songDetailsDiv = { innerHTML: '', style: {} }; // Mocking DOM element
        showSong(songTitle, songDetailsDiv); // Pass mock DOM element

        expect(songDetailsDiv.innerHTML).toBe('Song not found.');
    });

    it('should display the correct number of lyrics lines', () => {
        const songTitle = "Stairway to Heaven";
        const song = songsData.songs.find(song => song.title === songTitle);
        
        const songDetailsDiv = { innerHTML: '', style: {} }; // Mocking DOM element
        showSong(songTitle, songDetailsDiv); // Pass mock DOM element

        const lyricsLineCount = songDetailsDiv.innerHTML.split('<br>').length;
        expect(lyricsLineCount).toBe(song.lyrics.length);
    });
});