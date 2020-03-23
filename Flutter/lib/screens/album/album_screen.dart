import 'package:audino/api/albums.dart';
import 'package:audino/models.dart';
import 'package:audino/widgets/audino_scaffold.dart';
import 'package:audino/widgets/track_item.dart';
import 'package:flutter/material.dart';
import 'package:audino/widgets/image_gradient_background.dart';
import 'package:audino/widgets/audino_button.dart';
import 'package:audino/screens/routes.dart';

class AlbumScreen extends StatefulWidget {
  final String albumId;
  final String artistName;

  const AlbumScreen({Key key, this.albumId, this.artistName}) : super(key: key);

  @override
  _AlbumScreenState createState() => _AlbumScreenState();
}

class _AlbumScreenState extends State<AlbumScreen> {
  bool loading = true;
  FullAlbum album;

  @override
  void initState() {
    super.initState();
    load();
  }

  void load() async {
    final _album = await fetchAlbum(widget.albumId);
    setState(() {
      loading = false;
      album = _album;
    });
  }

  void onPressed(BuildContext context, List<Track> tracks) {
    Navigator.push(context, Routes.play(tracks.first, tracks));
  }

  @override
  Widget build(BuildContext context) {
    return AudinoScaffold(
      body: buildBody(),
    );
  }

  Widget buildBody() {
    if (loading) {
      return Center(child: CircularProgressIndicator());
    }

    final enabledTracks = album.tracks.where((track) => track.previewUrl != null).toList();

    return ListView(
        children: <Widget>[
          ImageGradientBackground(
            backgroundUrl: album.image,
            foreground: Column(
              children: <Widget>[
                Row(
                  children: <Widget>[
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          Text(
                            album.name,
                            overflow: TextOverflow.fade,
                            style: const TextStyle(
                              fontSize: 32,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          Text(
                            '${widget.artistName} \u25CF ${album.tracks.length} músicas',
                            softWrap: false,
                            overflow: TextOverflow.fade,
                            style: const TextStyle(
                              fontSize: 14,
                              fontWeight: FontWeight.w500,
                              color: const Color(0xFFF5F5F5),
                            ),
                          ),
                        ],
                      ),
                    ),
                    SizedBox(width: 8),
                    AudinoButton(
                      child: Text('TOCAR'),
                      onPressed: enabledTracks.isNotEmpty ? () => onPressed(context, enabledTracks) : null,
                    )
                  ],
                ),
              ],
            ),
          ),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              for (final track in album.tracks)
                TrackItem(
                  track: track,
                  tracks: album.tracks,
                ),
            ],
          ),
        ],
    );
  }
}
